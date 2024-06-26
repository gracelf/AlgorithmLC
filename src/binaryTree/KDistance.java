

package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class KDistance {
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {        
        Map<TreeNode, TreeNode> parent = new HashMap();
        Queue<TreeNode> queue = new LinkedList();


        // build the map, relation of node and its parent
        dfs(root, null, parent);
        Set<TreeNode> seen = new HashSet();
        queue.add(target);
        seen.add(target);
        int level = 0;


        // typical bfs 
        while (!queue.isEmpty()) {            
            if(level == K) break;
            int size = queue.size();            
            for(int i = 0 ; i < size; i++) {                            
                TreeNode node = queue.poll();         
                if (node.left != null && !seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (node.right != null && !seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (par != null && !seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
            level++;
        }      


        // populate all values of node in queue
        List<Integer> ans = new ArrayList();
        for (TreeNode n: queue)
            ans.add(n.val);
        return ans;                   
    }

    
    public void dfs(TreeNode node, TreeNode rootParent, Map<TreeNode, TreeNode> parent) {
        if (node != null) {
            //key is node, value is its parent
            parent.put(node, rootParent);
            dfs(node.left, node, parent);
            dfs(node.right, node, parent);
        }
    }

}
