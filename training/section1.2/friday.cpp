/*
ID: yeahYeet
PROG: friday
LANG: C++
*/

#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
#include <cstring>
#include <vector>

using namespace std;

int getdays(int month, int year) {
	if (month == 2) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) return 29;
				return 28;
			}
			return 29;
		}
		return 28;
	}
	if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
	return 31;
}
int main(int argc, const char * argv[]) {
    ifstream fin ("friday.in");
    ofstream fout ("friday.out");
    int N;
    fin >> N;
    vector<int> count(7, 0);
    int day = 1;
    for (int i = 0; i < N; i++) {
        for (int j = 1; j <= 12; j++) {
            count[(day + 13) % 7]++;
            int augment = getdays(j,1900+i);
             day += augment;
        }
    }
    for (int i = 0; i < 7; i++) {
        if (i != 0) fout << " ";
        fout << count[i];
    }
    fout << endl;
    return 0;
}
