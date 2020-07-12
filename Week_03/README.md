## 递归
递归的解题思路：

```
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 

}
```

## 分治回溯

分治的解题思路：

- 分解（Divide）将大规模的问题分解成若干个规模更小但形式相同的子问题
- 解决（Conquer）如果当前问题的规模足够小，并可以直接解决的话，那么直接解决并返回解。否则，继续进行分解并递归求解分解后的子问题。
- 合并（Merge）将各个子问题合并，最终形成原问题的解。

```
def divide_conquer(problem, param1, param2, ...): 
  # recursion terminator 
  if problem is None: 
  print_result 
  return 
  # prepare data 
  data = prepare_data(problem) 
  subproblems = split_problem(problem, data) 
  # conquer subproblems 
  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
  …
  # process and generate the final result 
  result = process_result(subresult1, subresult2, subresult3, …)
  
  # revert the current level states
```

回溯的解题思路：

1. 路径：也就是已经做出的选择
2. 选择列表：也就是你当前可以做的选择。
3. 结束条件：也就是到达决策树底层，无法再做选择的条件。

```
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```