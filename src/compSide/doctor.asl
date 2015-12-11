/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start
	<-	.wait(1000);
		.send(scout,achieve,find_victims);
		.print("I have ordered scout to look the victims!").
		
+victim(C,X,Y)[source(A)]
	<- 	.print(A," found a ",C," victim at (",X,",",Y,")");
		!addlist.
		
+!addlist
	<-	.findall(victim(C,X,Y),victim(C,X,Y),L);
		.sort(L,SL);
		.min(L,victim(I,O,U));
		.print("Victims in order of priority: ",SL);
		.send(scout,tell,victim(I,O,U));
		!printmin(I,O,U).
		
		
+!printmin(C,X,Y)
	<-	if (C == 1) {.print("Priority victim is red at (",X,",",Y,")")};
		if (C == 2) {.print("Priority victim is blue at (",X,",",Y,")")};
		if (C == 3) {.print("Priority victim is green at (",X,",",Y,")")}.
