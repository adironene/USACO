/*
 ID: yeah yeet
 PROG: gift1
 LANG: C++
*/

#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
#include <cstring>
#include <vector>

using namespace std;

int main(int argc, const char * argv[]) {
    ifstream fin ("gift1.in");
    ofstream fout ("gift1.out");
    int numFriends, amtMoneynet, ng;
    fin >> numFriends;
    unordered_map<string, int> map;
    vector<string> name(numFriends);
    for (int i = 0; i < numFriends; i++) {
        fin >> name[i];
        map[name[i]] = 0;
    }
    string giver, givee;
    for (int i = 0; i < numFriends; i++) {
        fin >> giver;
        fin >> amtMoneynet >> ng;
        map[giver] -= amtMoneynet;
        if (ng == 0) continue;
        int tmp = amtMoneynet / ng;
        for (int j = 0; j < ng; j++) {
            fin >> givee;
            map[givee] += tmp;
            amtMoneynet -= tmp;
        }
        map[giver] += amtMoneynet;
    }
    for (int i = 0; i < numFriends; i++) 
        fout << name[i] << " " << map[name[i]] <<endl;
    return 0;
}
