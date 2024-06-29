import math

n = int(input())
cows = list(map(int, [*input()]))
edgeclusters = []
middle_clusters = []
cur_counter = 0
isedgecluster = True
index = 0;

# sort bin string into clusters of cows
for cow in cows:
    if cow == 0:
        if index ==0:
            isedgecluster = False;
        if(isedgecluster):
            edgeclusters.append(cur_counter)
            isedgecluster = False;
            cur_counter=0
        if cur_counter != 0:
            middle_clusters.append(cur_counter)
            cur_counter=0
    else:
        cur_counter+=1
    index+=1
if cur_counter!=0:
    edgeclusters.append(cur_counter)

if len(edgeclusters) ==0 and len(middle_clusters) ==0:
    print(0)
def divide_and_ceiling(numbers):
    return [math.ceil(number / 2) for number in numbers]

# max number of days possible per cluster
maxDays = edgeclusters + (divide_and_ceiling(middle_clusters))
# print("edge clusters are " + str(edgeclusters))
# print("middle clusters are " + str(middle_clusters))
# print("max days are " + str(maxDays))

# max day in total is the smallest amt of days
maxDay = min(maxDays)
# print("max day is " + str(maxDay))
def minCows(cows):
    return [math.ceil(number/(2*maxDay-1)) for number in cows]
# print('mincows for edge clusters')
# print(minCows(edgeclusters))
# print('mincows for center clusters')
# print(minCows(middle_clusters))
print(sum(minCows(edgeclusters))+sum(minCows(middle_clusters)))