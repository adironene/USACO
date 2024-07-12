def ceilingDiv(a, b):
    return (a + b - 1) // b
 
def solve():
    n = int(input())
    h = [int(x) for x in input().split()]
    a = [int(x) for x in input().split()]
    t = [int(x) for x in input().split()]
    print("t before is " + str(t))
    ord = [i for i in range(n)]
    print("order before sort is " + str(ord))
    ord.sort(key=lambda x: t[x])
    print("order is " + str(ord))
    print("t is " + str(t))
    
    ret = 0
    for ordi in range(n-1):
        i = ord[ordi]
        j = ord[ordi+1]
        if h[i] < h[j] and a[i] > a[j]:
            ret = max(ret, ceilingDiv(h[j] - h[i] + 1, a[i] - a[j]))
            print("for " + str(h[i]) + " and " + str(h[j]) + " ret is " + str(ret))
    for i in range(n):
        h[i] += a[i] * ret
    for ordi in range(n-1):
        i = ord[ordi]
        j = ord[ordi+1]
        if h[i] <= h[j]: return -1
    return ret
 
t = int(input())
for _ in range(t):
    print(solve())
    