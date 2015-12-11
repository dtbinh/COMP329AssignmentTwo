/* Initial beliefs and rules */

/* Initial goals */

!print_ready.

/* Plans */

+!print_ready
	:	scout_ready
	<-	.print("I have recieved orders from doctor to look for victims!").

+!print_ready
	:	not scout_ready
	<-	.print("I am awaiting orders from doctor!").

+!find_victims[source(A)]
	<-	+scout_ready;
		!print_ready;
		.print("Looking for victims...");
		//testing
		.send(A,tell,victim(3,0,0));
		.wait(1000);
		.send(A,tell,victim(2,0,0));
		.wait(1000);
		.send(A,tell,victim(1,0,6));
		.wait(1000);
		.send(A,tell,victim(1,2,3));
		.wait(1000);
		.send(A,tell,victim(1,4,3));
		.wait(1000);
		.send(A,tell,victim(1,5,0));
		.wait(1000);
		.send(A,tell,victim(3,6,6)).
		
+victim(C,X,Y)[source(A)]
	<-	if (C == 1) {.print(A," tells me the priority victim is red at (",X,",",Y,")")};
		if (C == 2) {.print(A," Tells me the priority victim is blue at (",X,",",Y,")")};
		if (C == 3) {.print(A," Tells me the priority victim is green at (",X,",",Y,")")}.

//unused
+hello[source(A)]
 	<-	.print("I receive an hello from ",A);
	 	.send(A,tell,hello).
