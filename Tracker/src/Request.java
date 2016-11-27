import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Lorenzo on 27/11/2016.
 */
public class Request {
    private String info_hash;
    private String peer_id;
    private String ip;
    private Integer port;
    private Integer uploaded;
    private Integer downloaded;
    private Integer left;
    private Integer event;
    private Integer error;
    private enum eventStatus{
        started,
        stopped,
        completed
    }
    private Integer numwant;

    int createRequestFromGETrequest(BufferedReader request){
        String FirstLine = null;
        try {
            FirstLine = request.readLine();
            if (!FirstLine.contains("GET")) {
                error = 100;
                return error;
            } else {
                FirstLine = FirstLine.replace("GET", "").trim();
                FirstLine = FirstLine.substring(0, FirstLine.indexOf("HTTP"));
            }
            FirstLine = FirstLine.replace("?", "").replace("/", "");
            //now i have clear request
            String[] data = FirstLine.split("&");

            for (String pair : data) {
                String[] dividedPair = pair.split("=");
                dividedPair[0] = dividedPair[0].toLowerCase();
                if (!dividedPair[0].isEmpty() && dividedPair[0].equals("info_hash")) {
                    info_hash = dividedPair[1];
                } else {
                    if (dividedPair[0].equals("peer_id")) {
                        peer_id = dividedPair[1];
                    } else {
                        if (dividedPair[0].equals("ip")) {
                            ip = dividedPair[1];
                        } else {
                            if (dividedPair[0].equals("port")) {
                                try {
                                    port = Integer.parseInt(dividedPair[1]);
                                } catch (NumberFormatException e) {
                                    error = 103;
                                    return error;
                                }
                            } else {
                                if (dividedPair[0].equals("downloaded")) {
                                    try {
                                        downloaded = Integer.parseInt(dividedPair[1]);
                                    } catch (NumberFormatException e) {
                                        error = 900;
                                        return error;
                                    }
                                } else {
                                    if (dividedPair[0].equals("left")) {
                                        try {
                                            left = Integer.parseInt(dividedPair[1]);
                                        } catch (NumberFormatException e) {
                                            error = 900;
                                            return error;
                                        }
                                    } else {
                                        if (dividedPair[0].equals("event")) {
                                            try {
                                                event = eventStatus.valueOf(dividedPair[1]).ordinal();
                                            } catch (IllegalArgumentException | NullPointerException e) {
                                                error = 900;
                                                return error;
                                            }
                                        } else {
                                            if (dividedPair[0].equals("uploaded")) {
                                                try {
                                                    uploaded = Integer.parseInt(dividedPair[1]);
                                                } catch (NumberFormatException e) {
                                                    error = 900;
                                                    return error;
                                                }
                                            }else {
                                                if (dividedPair[0].equals("numwant")) {
                                                    try {
                                                        uploaded = Integer.parseInt(dividedPair[1]);
                                                    } catch (NumberFormatException e) {
                                                        error = 900;
                                                        return error;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        //change it, not 400
        return 400;
    }

}
