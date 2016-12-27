import sys
class Solution:
    def bubbleSort(self, nums):
        for i in range(len(nums)- 1):
            for j in range(i+1, len(nums)):
                if nums[i] > nums[j]:
                    nums[i],nums[j] = nums[j],nums[i]

def main(argv=None):
    if argv is None:
        argv = sys.argv
    nums  = [-1, 0, 5, 4, 2, 1, 8]
    Solution().bubbleSort(nums)
    print nums

if __name__ == '__main__':
    main()

