class findMaxMin {
    public int[] findMaxMin(int[] nums,int low,int high){
    if(low==high)//Base case as if when only one single element is left then
        // max=min so the array returns same element
    {
        return new int[]{nums[low],nums[low]};
    }
    else{
        //divide: dividing array into halves till only one element is left
        //i.e. low=high then return[a[low],a[low]]
        int mid=(low+high)/2;
        /*conquer: first all the left children are called and when it reaches its
        first base case, the returned value is saved in left array then it calls the
        right child of current node and the base case returned value is stored in right
        array. after that the left and right array are compared and the returned array
         is saved in left if it is a left subtree otherwise in right- draw recursion tree
         for better understanding*/
        int[] left= findMaxMin(nums,low,mid);
        int[] right=findMaxMin(nums,mid+1,high);
        int min=Math.min(left[0],right[0]);
        int max=Math.max(left[1],right[1]);
        return new int[]{min,max};
    }
    }
}
public class findMaxAndMin{
public static void main(String[] args) {

    int[] arr = {8, 3, 5, 1, 9, 2, 7, 6};

    findMaxMin obj = new findMaxMin();

    int[] ans = obj.findMaxMin(arr, 0, arr.length - 1);

    System.out.println("Minimum = " + ans[0]);
    System.out.println("Maximum = " + ans[1]);
}}

