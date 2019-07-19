public class Message {

    private String msg;
    private int senderIP;
    private int receiverIP;
    private HTTPRequest type;

    public Message(String msg, int senderIP, int receiverIP, HTTPRequest type){

        this.msg = msg;
        this.senderIP = senderIP;
        this.receiverIP = receiverIP;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSenderIP() {
        return senderIP;
    }

    public void setSenderIP(int senderIP) {
        this.senderIP = senderIP;
    }

    public int getReceiverIP() {
        return receiverIP;
    }

    public void setReceiverIP(int receiverIP) {
        this.receiverIP = receiverIP;
    }

    public HTTPRequest getType() {
        return type;
    }

    public void setType(HTTPRequest type) {
        this.type = type;
    }
}
