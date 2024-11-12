package practical;

//Pass 2:
import java.util.*;
import java.io.*;

class Macro_pass2{
public static void main(String args[]) {
pass2();
System.out.println("Argument List Array(ALA) for Pass2");
display(Macro_pass1.ala, Macro_pass1.alac, 2);
System.out.println("Note: All tables are displayed here whereas the expanded output is stored in the file pass2_output.txt");
}

static void pass2() {
int alap = 0, index, mdtp, flag = 0, i, j;
String s, temp;
try {
BufferedReader inp = new BufferedReader(new FileReader("D:\\java\\eclipse-workplace\\SPOS\\src\\practical\\pass1_output.txt"));
File op = new File("D:\\java\\eclipse-workplace\\SPOS\\src\\practical\\pass2_output.txt");
if (!op.exists())
op.createNewFile();
BufferedWriter output = new BufferedWriter(new FileWriter(op.getAbsoluteFile()));

for (; (s = inp.readLine()) != null; flag = 0) {
StringTokenizer st = new StringTokenizer(s);
String str[] = new String[st.countTokens()];
for (i = 0; i < str.length; i++)
str[i] = st.nextToken();
for (j = 0; j < Macro_pass1.mntc; j++) {
if (str[0].equals(Macro_pass1.mnt[j][1])) {
mdtp = Integer.parseInt(Macro_pass1.mnt[j][2]);

st = new StringTokenizer(str[1], ",");
String arg[] = new String[st.countTokens()];
for (i = 0; i < arg.length; i++) {
arg[i] = st.nextToken();
Macro_pass1.ala[alap++][1] = arg[i];
}
for (i = mdtp; !(Macro_pass1.mdt[i][0].equalsIgnoreCase("MEND")); i++) { // Expand untilMEND
index = Macro_pass1.mdt[i][0].indexOf("#");
temp = Macro_pass1.mdt[i][0].substring(0, index);
temp += Macro_pass1.ala[Integer.parseInt("" + Macro_pass1.mdt[i][0].charAt(index + 1))][1]; //Convert char->string->integer & append it
output.write(temp);
output.newLine();
}
flag = 1;
}
}
if (flag == 0) { // When it is not a macro
output.write(s);
output.newLine();
}
}
output.close();
} catch (FileNotFoundException ex) {
System.out.println("Unable to find file ");
} catch (IOException e) {
e.printStackTrace();
}
}

static void display(String a[][], int n, int m) {

int i, j;
for (i = 0; i < n; i++) {
for (j = 0; j < m; j++)
System.out.print(a[i][j] + " ");
System.out.println();
}
}
}
//
//Output:
//Argument List Array(ALA) for Pass2
//0 DATA1
//1 DATA2
//2 DATA3
//3 DATA4
//PRG2 START
//USING *,BASE
//A 1,DATA1
//L 2,DATA2
//L 3,DATA3
//ST 4,DATA4
//FOUR DC F'4'
//FIVE DC F'5'
//BASE EQU 8
//TEMP DS 1F
//DROP 8
//END

