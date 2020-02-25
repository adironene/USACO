#include <stdio.h>
#include <cassert>
#include <iostream>
#include <fstream>
#include <string>

bool check(long long n, long long k, long long m, long long x)
{
    long long g = 0, t = 0;
    while (t < k)
    {
        long long y = (n - g) / x;
        //if calculated y value is smaller than the min
        //it will remain so for the rest of the days
        if (y <= m)
        {
            g += (k - t) * m;
            t = k;
        }
        else
        {
            //our code has to run within time
            //we reduce the amt of computations
            //y will remain y until (n-g) yields a dif module of x bc rounded down
            long long mod = (n - g) % x;
            //skip some calculations and also +y and +1
            //bc might be last day
            long long changeDays = mod / y;
            g += changeDays * y + y;
            t += changeDays + 1;
        }
    }
    return g >= n;
}
int main()
{
    //scan givens
    std::ifstream fin("loan.in");
    std::ofstream fout("loan.out");
    long long n, k, m;
    //set low and high bounds
    //using a binary search
    long long low = 1, high = 1e12;
    fin >> n >> k >> m;
    while (low < high)
    {
        long long mid = (low + high + 1) / 2;
        if (check(n, k, m, mid))
            low = mid;
        else
        {
            high = mid - 1;
        }
    }
    fout << high << endl;
    return 0;
}
