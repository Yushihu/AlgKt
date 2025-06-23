import io.yushihu.tree.binary.avl.Tree
import io.yushihu.tree.binary.avl.TreeNode
import io.yushihu.tree.binary.common.bfs
import io.yushihu.tree.binary.common.preorderTraversal
import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.math.max

class AVLNode(value: Int): TreeNode<Int, AVLNode> {
    override var valueProp: Int = value
    override var heightProp: Int = 1
    override var leftProp: AVLNode? = null
    override var rightProp: AVLNode? = null
}

class AVLTree(
    override val threshold: Int = 2,
): Tree<Int, AVLNode> {
    init {
        if (threshold < 2) {
            error(IllegalArgumentException("Threshold must be at least 2"))
        }
    }
    override var root: AVLNode? = null
    override fun createNode(value: Int): AVLNode {
        return AVLNode(value)
    }
}

class AVLTreeTest {
    private val utils = BSTUtils()
    private val threshold = 2

    @Test
    fun testCRD() {
        utils.testCRD(AVLTree(), samples = utils.randSamples(100000))
    }

    @Test
    fun testBalance() {
        val tree = AVLTree(threshold)
        val samples = utils.randSamples(1000, 0..50)
        samples.shuffled()
            .forEach {
                tree.insert(it)
                assert(tree.isBalanced())
            }
        samples.shuffled().forEach {
            tree.deleteFirst(it)
            assert(tree.isBalanced())
        }
    }

    fun AVLTree.isBalanced(): Boolean {
        fun check(node: AVLNode?): Int {
            if (node == null) return 0
            val leftHeight = check(node.left)
            assert(leftHeight == node.leftHeight)
            val rightHeight = check(node.right)
            assert(rightHeight == node.rightHeight)
            val balanceFactor = leftHeight - rightHeight
            if (abs(balanceFactor) > threshold) {
                error(IllegalStateException(
                    "Tree is unbalanced at node ${
                        node.valueProp
                    }: left height = ${
                        leftHeight
                    }, right height = ${
                        rightHeight
                    }, balance factor = $balanceFactor"
                ))
            }
            return max(leftHeight, rightHeight) + 1
        }
        return check(root) != -1
    }

}
