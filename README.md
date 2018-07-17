# Legend of Zelda
Zelda Game written in Java *(W.I.P)*

## The game loop:
![alt text](ex_1.png?raw=true "Title")
## The State system:
![alt text](ex_2.png?raw=true "Title")

### My program read comma-seperated text files and outputs a map using the following function:

```
mapLoad(x, y):

load file(x + y + ".txt")

for i in cols:
  read and split and store in nums
  for j in rows:
    temp = nums[j]
    # rows * i + j is a formula that can tread 1D array as 2D array
    tiles[rows * i + j]
    
    rectangle_tiles[rows * i + j] = new rectangle(j * width, i * height + shift, width, height
```
### Where can Zelda walk?
```
if not grass:
  walkable[16 * i + j] = true
else
  walkable[16 * i + j] = false
  
![alt text](https://media.giphy.com/media/BzkSetWnfsembS05vB/giphy.gif)



