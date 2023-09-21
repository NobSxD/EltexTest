package parser

import com.sun.net.httpserver.HttpsParameters

class Url {
    String getUrlContent(String urlAdress) {
        StringBuffer conect = new StringBuffer();
        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(30000)

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                conect.append(line + "\n");
            }
        } catch (Exception e) {
            println ("Ошибка не получили данные с url: " + e.getMessage());
        }
        return conect.toString();
    }




}
