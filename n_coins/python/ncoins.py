#!/bin/python

import sys


def solution(coins=[]):
    length = len(coins)
    sums = [0] * (length+1)
    sums[0] = 0
    for i in range(1, length+1):
        sums[i] = sums[i-1] + coins[i-1]

    firstMax = [[0 for i in range(length)] for j in range(length)]
    firstMax[0] = coins
    for i in range(1, length):
        for j in range(0, length-i):
            valueIfPickHead = coins[j] + sums[j+i+1] - sums[j+1]\
                                - firstMax[i-1][j+1]
            valueIfPickTail = coins[j+i] + sums[j+i] - sums[j]\
                                - firstMax[i-1][j]
            firstMax[i][j] = max(valueIfPickHead, valueIfPickTail)
    if firstMax[length-1][0] * 2 > sums[length]:
        firstWillWin = True
    else:
        firstWillWin = False
    return (firstWillWin, firstMax)

def main(argv=None):
    [firstWin, steps] = solution(range(1,2))
    print "first player will? " + str(firstWin)
    print steps

if __name__ == "__main__":
    sys.exit(main())



