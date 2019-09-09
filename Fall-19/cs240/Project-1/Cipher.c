#include <stdio.h>
int key = ~0;

int  decrypt() {

	int keyTemp;
        int counter = 0;
	int charCount = 0;
        char c = getchar();
        while ( (int)c != 27 ) {
		
		charCount++;

                if ( c == ' ' || c == '\t' || c == '\n' ) {
                        putchar(c);
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
                c = getchar();
        }

	return charCount;
}

void encrypt(int charCount ) {
	
	int keyTemp;
	int counter = 0;
	char c;
	while ( charCount >= 0 ) {
		c = getchar();
		if ( c == ' ' || c == '\t' || c == '\n' ) {
			putchar(c);
		} else {
			if ( counter % 4 == 0 ) {
				keyTemp = key;
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
	scanf("%d %d\n",&keyD,&keyE);
	getchar();
	return keyE ^ keyD;

}
int main() {
	printf("Welcome to Cipher\n");

	while(1) {
		printf("Choose an option:\nA Encrpyt Text\nB Decrypt Text\nC Find Key\nD Exit\n");
		char ans = getchar();
		if( ans == 'D' ){
			break;
		} else if( ans == 'A' ) {
			int charCount;
			scanf("%d\n",&charCount);
			encrypt(charCount);
		} else if( ans == 'B' ) {
			printf("%d\n",decrypt());
		} else if( ans == 'C' ) {
			key = findKey();
		} else {
			printf("Invalid Input\n");
		}
	}
}
