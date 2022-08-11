/*
       Implementación de un validador para direcciones IP
   Public boolean solution(String ip) { … }
   “Hola este es un texto” -> false
   “192.168.1” -> false
   “10.15.30.300” -> false
   “192.168.1.100” -> true
    */
public class IpValidator {

    public static void main(String[] args) {

        System.out.println(ipValidator("Hola este es un texto"));
        System.out.println(ipValidator("192.168.1"));
        System.out.println(ipValidator("10.15.30.300"));
        System.out.println(ipValidator("192.168.1.100"));
    }
    public static boolean ipValidator(String ip) {
        String[] initialArray = ip.split("\\.");
        //System.out.println(initialArray.length);
        if(initialArray.length != 4) {
            return false;
        }
        for(int i= 0; i<initialArray.length;i++) {
            if(Integer.valueOf(initialArray[i]) > 0 && Integer.valueOf(initialArray[i]) <256) {

            } else {
                return false;
            }
        }
        return true;
    }
}
