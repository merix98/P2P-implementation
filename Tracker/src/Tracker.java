class Tracker {
    public static void main(String[] args){
        //todo options
        new Thread(new ServerTask(4242)).start();
    }
}