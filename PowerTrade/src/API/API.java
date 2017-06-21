package API;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.JsonArray;

import Nexus.CommandCenter;

public class API {

	private HttpURLConnection conn;

	public String formatCommand(Map<String, String> command) throws Exception {
		StringBuilder paramData = new StringBuilder();
		for (Map.Entry<String, String> param : command.entrySet()) {
			if (paramData.length() != 0)
				paramData.append('&');
			paramData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			paramData.append('=');
			paramData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
		}

		String sCommand = paramData.toString();
		return sCommand;
	}

	public Response sendPostRequest(URL url, String[] headers, String pCommand) throws Exception {
		
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		setHeader(headers);
		byte[] byteCommand = pCommand.getBytes("UTF-8");
		
		conn.getOutputStream().write(byteCommand);

		Response response = readResponse();
		return response;
	}

	public Response sendGetRequest(URL gCommand) throws Exception {
		conn = (HttpURLConnection) gCommand.openConnection();
		conn.setRequestMethod("GET");

		Response response = readResponse();
		return response;
	}

	private void setHeader(String[] headers) {
		for (int x = 0; x < headers.length; x += 2) {
			conn.setRequestProperty(headers[x], headers[x + 1]);
		}
	}

	private Response readResponse() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (String line; (line = in.readLine()) != null;)
			sb.append(line);
		String response = sb.toString();
		
		return new Response(CommandCenter.parseJSON(response),conn.getResponseCode());
	}

	public class Response {
		private JsonArray responseMsg;
		private int responseCode;

		public Response(JsonArray message, int responseCode) {
			this.responseMsg = message;
			this.responseCode = responseCode;
		}

		public JsonArray getResponseMsg() {
			return responseMsg;
		}

		public int getResponseCode() {
			return responseCode;
		}
		
		
	}

}
