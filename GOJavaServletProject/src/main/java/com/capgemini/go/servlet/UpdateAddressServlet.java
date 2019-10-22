
    
package com.capgemini.go.servlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.capgemini.go.dto.AddressDTO;
import com.capgemini.go.dto.FrequentOrderedDTO;
import com.capgemini.go.service.RetailerService;
import com.capgemini.go.service.RetailerServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class UpdateAddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


@SuppressWarnings("unchecked")
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        


        StringBuffer jb = new StringBuffer();
          String line = null;
          try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
              jb.append(line);
          } catch (Exception e) {  }
        Map<String,String> myMap = new HashMap<String, String>();


        ObjectMapper objectMapper = new ObjectMapper();
        
        myMap = objectMapper.readValue(jb.toString(), HashMap.class);
    
        String addressId = myMap.get("addressId");
        String retailerId = myMap.get("userId");
        String city = myMap.get("city");
        String state = myMap.get("state");
        String zip = myMap.get("zip");
        String buildingNo = myMap.get("building_number");
        String country = myMap.get("country");
      
        
    
                
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        response.setHeader("Access-Control-Allow-Headers" ,"Content-Type, Authorization, Content-Length, X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataResponse = mapper.createObjectNode();
        boolean result=false;
        PrintWriter out =response.getWriter();
        RetailerService retailer = new RetailerServiceImpl ();
            
            AddressDTO updateaddress = new AddressDTO(addressId, retailerId, city, state, zip, buildingNo, country, true);
                try {
                    result = retailer.updateAddress(updateaddress);
                
                if(result)
                {
                    ((ObjectNode) dataResponse).put("Success :","Address updated");
                }
                
            } catch ( Exception e) {
                
                ((ObjectNode) dataResponse).put("Error :",e.getMessage());
                
            }
            finally
            {
                out.print(dataResponse);
                out.close();
            }
            
            
        


}
}
 











