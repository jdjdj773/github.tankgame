package Test;

public class ValidParentheses {
        public static void main(String args[]) {
            char a = '(';
            char b = ')';
            char c = '{';
            char d = '}';
            char e = '[';
            char f = ']';
            boolean ans;
            int temp = 0;
            String s = "{}";
            for(int i=0;i < s.length();i+=2){
                for(int j=1;j < s.length();j+=2){
                    if((s.charAt(i)+s.charAt(j)) == (a+b) || (s.charAt(i)+s.charAt(j))== (c+d)
                            || (s.charAt(i)+s.charAt(j)) == (e+f)){
                        temp++;
                    }
                }
            }
            if(temp == (s.length()/2) && (s.length()/2) == 0){
                ans = true;
            }else{
                ans = false;
            }
            System.out.print("ans=" + ans + " " +"temp=" +temp);
        }
}
