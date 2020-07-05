object InorderTraversal {

  def inOrderTraversal(root: TreeNode): List[Int] = {
      if (root == null) {return Nil}
      inOrderTraversal(root.left) ::: List(root.value) ::: inOrderTraversal(root.right)
  } 
  
}
