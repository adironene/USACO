/*
 ID: yea yeet
 PROG: beads
 LANG: C++
*/

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <cstring>
#define white 'w'
#define red 'r'
#define blue 'b'

using namespace std;

int main(int argc, const char *argv[])
{
    ifstream fin("beads.in");
    ofstream fout("beads.out");
    string s;
    int numBeads;
    bool isRed = s[0] != 'b';
    fin>>numBeads>>s;
    char first = s[0];
    bool allSame = true;
    for (int i = 0; i < numBeads; i++)
    {
        if (s[i] != first&&s[i] !=white)
        {
            allSame = false;
            break;
        }
    }
    if (allSame)
    {
        fout << numBeads<<endl;
        return 0;
    }
    s = s + s;
    int r = 0, b = 0, w = 0;
    int answer = 0;
    for (int i = 0; i < s.length(); i++) {
        if (isRed) {
            if (s[i] == blue) {
                answer = max(answer, r + b + w);
                b = w + 1;
                w = 0;
                isRed = false;
            } else if (s[i] == white) {
                w++;
            } else {
                r += w + 1;
                w = 0;
            }
        } else {
            if (s[i] == red) {
                answer = max(answer, r + b + w);
                r = w + 1;
                w = 0;
                isRed = true;
            } else if (s[i] == white) {
                w++;
            } else {
                b += w + 1;
                w = 0;
            }
        }
    }
    fout << min(answer, numBeads) << endl;
    return 0;
}
