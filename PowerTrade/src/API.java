import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class API {
	
	public URL url;
     HttpURLConnection conn;
    
    
    public API(URL url)
    {
    	this.url=url;
    }
    
	public void openConn() throws Exception 
	{
		if(conn == null) conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);

	}
    
	public String formatCommand(Map<String,Object> command) throws Exception
	{
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String,Object> param : command.entrySet()) 
	    {
	        if (postData.length() != 0) postData.append('&');
	        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	        postData.append('=');
	        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	    }
	    
		String sCommand = postData.toString();
		return sCommand;
	}

	public String sendPostRequest(String fCommand) throws Exception
	{
		byte[] byteCommand = fCommand.getBytes("UTF-8");	    
		conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    conn.setRequestProperty("Content-Length", String.valueOf(byteCommand.length));
	        
	    conn.getOutputStream().write(byteCommand);
	    String response = readResponse();
		return response;
	}

	private String readResponse() throws Exception
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (String line; (line = in.readLine())!= null;)
            sb.append(line);
        String response = sb.toString();
		return response;
	}

}
