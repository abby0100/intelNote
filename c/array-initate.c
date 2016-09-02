#include <stdio.h>

int main() {

	int array[] = {[0 ... 5] = 20};
	int idx = 0;
	for(;idx < 6; idx++) {
		printf("array[%d] = %d\n", idx, array[idx]);
	}
}


