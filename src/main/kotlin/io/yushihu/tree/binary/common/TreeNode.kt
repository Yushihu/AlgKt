package io.yushihu.tree.binary.common

interface TreeNode<V, T: TreeNode<V, T>> {
    val left: T?
    val right: T?
    val value: V

}

fun <V, T: TreeNode<V, T>> T.goingLeft() = iterator {
    var cur = this@goingLeft
    while (true) {
        cur = cur.left ?: break
        yield(cur)
    }
}

fun <V, T: TreeNode<V, T>> T.goingRight() = iterator {
    var cur = this@goingRight
    while (true) {
        cur = cur.right ?: break
        yield(cur)
    }
}

fun <V, T: TreeNode<V, T>> T.getLeftMost(): T {
    var cur = this
    goingLeft().forEach {
        cur = it
    }
    return cur
}

fun <V, T: TreeNode<V, T>> T.getRightMost(): T {
    var cur = this
    goingRight().forEach {
        cur = it
    }
    return cur
}

fun <V, T: TreeNode<V, T>> T?.preorderTraversal() = iterator {
    val stack = mutableListOf<T>()
    var cur = this@preorderTraversal
    while (true) {
        yield(cur)
        if (cur == null) {
            if (stack.isEmpty()) break
            cur = stack.removeLast().right
        } else {
            stack.add(cur)
            cur = cur.left
        }
    }

}

fun <V, T: TreeNode<V, T>> T?.bfs() = iterator {
    var boarder = mutableListOf<T?>(this@bfs)
    while (!boarder.isEmpty()) {
        yieldAll(boarder)
        val nextBoarder = mutableListOf<T?>()
        boarder.forEach { node ->
            if (node != null) {
                nextBoarder.add(node.left)
                nextBoarder.add(node.right)
            }
        }
        boarder = nextBoarder
    }

}
