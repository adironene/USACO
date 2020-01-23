/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: yeahyeet
TASK: ride
LANG: C++                 
*/
/* LANG can be C++11 or C++14 for those more recent releases */
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main()
{
    ofstream fout("ride.out");
    ifstream fin("ride.in");
    string a, b;
    fin >> a >> b;
    int val1 = 1, val2 = 1;
    for (int i = 0; i < a.size(); ++i)
        val1 *= a[i] - 'A' + 1;
    for (int i = 0; i < b.size(); ++i)
        val2 *= b[i] - 'A' + 1;
    fout << (val1 % 47 == val2 % 47 ? "GO" : "STAY") << endl;
    return 0;
}
