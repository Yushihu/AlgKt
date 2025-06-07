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
