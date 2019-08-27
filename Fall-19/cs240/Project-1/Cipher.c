#include <stdio.h>

int key = ~0;

void  decrypt() {
	
}

void encrypt(int charCount ) {
	
	int keyTemp;
	int counter = 0;
	char c;
	while ( charCount >= 0 ) {
		c = getchar();
		if ( c == ' ' || c == '\t' || c == '\n' ) {
			puchar(c);
		} else {
			if ( counter % 4 == 0 ) {
				keyTemp = 0;
			} else {
				keyTemp >>= 4;
			}
			char temp = (char) (15 & keyTemp);
			c ^= temp;
			putchar(c);
			counter++;
		}
		charCount--;
	}

}

int findKey() {
	
	int keyE,keyD;
	scanf("%d %d\n",keyD,keyE);
	return keyE ^ keyD;

}
int main() {
	printf("Welcome to Cipher\n");

	while(1) {
		printf("Choose an option:\nA Encrpyt Text\nB Decrypt Text\nC Find Key\nD Exit\n);
		char ans = getchar();
		if( ans == 'D' ){
			break;
		} else if( ans == 'A' ) {
			int charCount;
			scanf("%d\n",charCount);
			encrypt(charCount);
		} else if( ans == 'B' ) {
			decrypt();
		} else if( ans == 'C ) {
			findKey();
		} else {
			printf("Invalid Input\n");
		}
	}
}
