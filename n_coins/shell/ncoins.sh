#!/bin/sh

COINS=($(seq 1 5))
LEN=${#COINS[@]}
SUMS=(0)

for ((k=1; k<$LEN+1; k=k+1))
do
	SUMS[k]=$((${SUMS[$k-1]}+${COINS[$k-1]}))
#	echo ${SUMS[$k]}
done

FIRSTMAX=()
for ((i=0; i<$LEN+1; i=i+1))
do
	FIRSTMAX[i]=${COINS[$i]}	
done


for ((i=1; i<$LEN;i=i+1))
do
	for ((j=0; j+$i<$LEN; j=j+1))
	do
		TEMP_HEAD=$((${COINS[$j]}+${SUMS[$j+$i-1]}-${SUMS[$j+1]}-${FIRSTMAX[$j+1]}))
		TEMP_TAIL=$((${COINS[$j+$i]}+${SUMS[$j+$i]}-${SUMS[$j]}-${FIRSTMAX[$j]}))
		if [ $TEMP_HEAD -gt $TEMP_TAIL ] 
		then
			FIRSTMAX[$j]="$TEMP_HEAD "
		else
			FIRSTMAX[$j]="$TEMP_TAIL "
		fi
		echo -n "${FIRSTMAX[$j]} "
	done
	echo
done



#echo ${SUMS[@]}
