package com.capgemini.go.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.go.dao.QuerryMapper;
import com.capgemini.go.exception.DatabaseException;
import com.capgemini.go.exception.IdGenerationException;

public class GenerateOrderID {

	/*******************************************************************************************************
	 * - Function Name : generateOrderID - Input Parameters :connection Return Type
	 * :String Throws :- Author : Agnibha Creation Date : 21/9/2019 - Description :
	 * to authenticate the user
	 * 
	 * @throws IdGenerationException
	 ********************************************************************************************************/
	public static String generate() throws IdGenerationException {
		try {
			Connection connection = DbConnection.getInstance().getConnection();
			Statement Stmt = connection.createStatement();
			ResultSet resultSet = Stmt.executeQuery(QuerryMapper.ORDER_COUNT);
			resultSet.next();
			int count = resultSet.getInt(1)+1;
			return Integer.toString(count);
		} catch (DatabaseException | SQLException e) {
			throw new IdGenerationException(e.getMessage());
		}
	}

}
