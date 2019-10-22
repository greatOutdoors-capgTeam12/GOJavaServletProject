package com.capgemini.go.utility;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.capgemini.go.dao.QuerryMapper;
import com.capgemini.go.exception.AuthenticationException;
import com.capgemini.go.exception.DatabaseException;

public class Authentication {

	/*******************************************************************************************************
	 * - Function Name : encrypt - Input Parameters : password,key Return Type
	 * :String Throws : AuthenticationException- Author : Agnibha Creation Date :
	 * 21/9/2019 - Description : to encrypt the password
	 ********************************************************************************************************/

	public static String encrypt(String strClearText, String strKey) throws AuthenticationException {
		String strData = "";

		try {

			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(strClearText.getBytes());
			strData = new String(encrypted);

		} catch (Exception e) {

			throw new AuthenticationException(e.getMessage());
		}
		return strData;
	}

	/*******************************************************************************************************
	 * - Function Name : decrypt - Input Parameters : password,key Return Type
	 * :String Throws : AuthenticationException- Author : Agnibha Creation Date :
	 * 21/9/2019 - Description : to Decrypt the password
	 ********************************************************************************************************/
	public static String decrypt(String strEncrypted, String strKey) throws AuthenticationException {
		String strData = "";

		try {

			SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, skeyspec);
			byte[] decrypted = cipher.doFinal(strEncrypted.getBytes());
			strData = new String(decrypted);

		} catch (Exception e) {

			throw new AuthenticationException(e.getMessage());
		}
		return strData;
	}

	/*******************************************************************************************************
	 * - Function Name : authenticate - Input Parameters : String userId,int
	 * category, Connection connection Return Type :boolean Throws :- Author :
	 * Agnibha Creation Date : 21/9/2019 - Description : to authenticate the user
	 * @throws ConnectException 
	 ********************************************************************************************************/
	public static boolean authenticateUser(String userId, int category) throws ConnectException {
		boolean authenticationStatus = false;
		Connection connection = null;
		try {
			connection  = DbConnection.getInstance().getConnection();
			PreparedStatement prestmt = connection.prepareStatement(QuerryMapper.GET_USER_STATUS);
			prestmt.setString(1, userId);
			ResultSet resultSet = prestmt.executeQuery();
			if (resultSet.next() == false) {
				System.out.println(AuthenticationConstants.user_not_exists);
				return authenticationStatus;
			}

			if (resultSet.getInt(1) != 1) {
				System.out.println(AuthenticationConstants.login_error);
				return authenticationStatus;
			}
			if (resultSet.getInt(2) != category) {
				System.out.println(AuthenticationConstants.access_error);
				return authenticationStatus;
			}
			authenticationStatus = true;
			System.out.print(AuthenticationConstants.welcome);

		} catch (DatabaseException | SQLException e) {

			GoLog.logger.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new ConnectException(Constants.connectionError);
			}
		}
		return authenticationStatus;
	}

}
