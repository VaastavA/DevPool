void myscanf(const char*,...);

int main(){

	char l;
	char str[80];
	
	int a = 0;
	myscanf("Integer %d ",&a);
	printf("Here lads: %d\n",a);
	myscanf("%c %s",&l,str);
	//printf(" string:%s  char:%c \n",str,l);
}
