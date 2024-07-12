n, m = map(int, input().split())
init_heights = list(map(int, input().split()))
ccane_heights = list(map(int, input().split()))

for candy in ccane_heights:
	if init_heights[0] >= candy:
		init_heights[0]+= candy
	else:
		eaten = 0
		for i in range(n):
			if init_heights[i] > eaten:
				justate = min(init_heights[i], candy)
				init_heights[i]+=justate - eaten
				eaten = justate
				

for cow in init_heights:
	print(cow)