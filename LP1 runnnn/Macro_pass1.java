package practical;

//Pass 1
import java.util.*;
import java.io.*;

class Macro_pass1 {
static String mnt[][] = new String[5][3]; // Assuming 5 macros in 1 program
static String ala[][] = new String[10][2]; // Assuming 2 arguments in each macro
static String mdt[][] = new String[20][1]; // Assuming 4 LOC for each macro
static int mntc = 0, mdtc = 0, alac = 0;

public static void main(String args[]) {
pass1();
System.out.println("Macro Name Table(MNT)");
display(mnt, mntc, 3);
System.out.println("Argument List Array(ALA) for Pass1");
display(ala, alac, 2);
System.out.println("Macro Definition Table(MDT)");
display(mdt, mdtc, 1);
}

static void pass1() {
int index = 0, i;
String s, prev = "", substring;
try {
BufferedReader inp = new BufferedReader(new FileReader("D:\\java\\eclipse-workplace\\SPOS\\src\\practical\\inputmac.txt"));
while ((s = inp.readLine()) != null) {
if (s.equalsIgnoreCase("MACRO")) {
prev = s;
for (; !(s = inp.readLine()).equalsIgnoreCase("MEND"); mdtc++, prev = s) {
if (prev.equalsIgnoreCase("MACRO")) {
StringTokenizer st = new StringTokenizer(s);

String str[] = new String[st.countTokens()];
for (i = 0; i < str.length; i++)
str[i] = st.nextToken();
mnt[mntc][0] = (mntc + 1) + ""; // MNT formation
mnt[mntc][1] = str[0];
mnt[mntc++][2] = (++mdtc) + "";
st = new StringTokenizer(str[1], ","); // Tokenizing the arguments
String string[] = new String[st.countTokens()];
for (i = 0; i < string.length; i++) {
string[i] = st.nextToken();
ala[alac][0] = alac + ""; // ALA table formation
index = string[i].indexOf("=");
if (index != -1)
ala[alac++][1] = string[i].substring(0, index);
else
ala[alac++][1] = string[i];
}
} else { // Automatically eliminates tagging of arguments in definition
index = s.indexOf("&");
substring = s.substring(index);
for (i = 0; i < alac; i++)
if (ala[i][1].equals(substring))
s = s.replaceAll(substring, "#" + ala[i][0]);
}
mdt[mdtc - 1][0] = s;
}
mdt[mdtc - 1][0] = s;
}
}
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

/*
Macro Name Table(MNT)
1 INCR1 1
2 INCR2 5
Argument List Array(ALA) for Pass1
0 &FIRST
1 &SECOND
2 &ARG1
3 &ARG2
Macro Definition Table(MDT)
INCR1 &FIRST,&SECOND=DATA9
A 1,#0
L 2,#1
MEND
INCR2 &ARG1,&ARG2=DATA5
L 3,#2
ST 4,#3

MEND
*/
