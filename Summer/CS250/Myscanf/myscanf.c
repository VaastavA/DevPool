void myscanf( const char* format,...){
	
	char* string;
	string = (char*)format;
	char** stack_pointer;
	stack_pointer = (char*)(&format);
	stack_pointer++;

	while(*string != '\0'){
		if(*string == '%'){
			string++;
			switch(*string){
				case '%': getchar();
					break;
				case 'c': *stack_pointer = getchar();
					printf("char:%c\n",*stack_pointer);
					stack_pointer++;
					break;
				case 's': ; int count = 0;
					char c;
					{
						while( (c = getchar() ) == ' ') continue;
						while( c != ' ' && c!= '\n'){
							*( *stack_pointer + count ) = c;
							printf("String char read: %c\n",c);
							count++;
							c = getchar();
							}
					}
					write(0,c,1);
					*(*stack_pointer + count ) = '\0';
					printf(" string:%s\n",*stack_pointer);
					stack_pointer++;
					break;
				case 'x':;break;
				case 'X':;break;
				case 'd': scand(*stack_pointer);
					break;
			}
		}else{
			char c;
			c = getchar();
			if (c == '\n') {
				printf("Newline found\n");
				return;
			}
		}
		string++;
	}
}
