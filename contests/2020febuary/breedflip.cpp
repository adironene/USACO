#include <iostream>
#include <math.h>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iostream>
#include <list>
using namespace std;

int main() {
  ifstream fin ("breedflip.in");
  ofstream fout ("breedflip.out");
  int nc;
  fin>> nc;
  string wanted, current;
  fin>>wanted>>current;
  vector<char> want(wanted.begin(), wanted.end());
  vector<char> have(current.begin(), current.end());
  int times =0;
  int i=0; 
  while(i<nc){
    if(want[i]==have[i]){
      i++;
      continue;
    }
    while(want[i]!=have[i]){
      i++;
      if(i==nc){
        times++;
        break;
      }
    }
      times++;
  }
  fout<<times<<endl;
  return 0;
}
