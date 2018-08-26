import middleware.Middleware;

class Client {

    public static void main(String[] args){

        Middleware mware = new Middleware();

        try{
            System.out.println("The answer is "+ mware.getParameters(args[0], Integer.parseInt(args[1]),Integer.parseInt(args[2])));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}