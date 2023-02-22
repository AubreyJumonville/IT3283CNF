# IT3283CNF

Discuss with your classmates and find two partners to form a team of 3 students and decide who will be the
corresponding student for this programming assignment. The corresponding student will be in charge of
submitting the report and preparing the programs on his/her Unix directory, and answering my questions
or comments, if any. After the team is formed, please use the ReggieNet to joint the team for my records.
On ReggieNet, under Site Info, there is a selection labeled as ”Groups you can join”. One of the team
member should select an empty group first for the other two to join.
The major work is divided for the three team members, Students A, B, and C as follows:
• Student A should write a method that takes an 3CNF ϕ in whatever data structures the team
decided to use as the input and returns an assignment that makes ϕ true, if such assignment exists.
However, Student A should not solve the problem directly. Instead, he/she should reduce the problem
in polynomial time to a corresponding minimum vertex cover problem for Student B to solve and
use the results (some minimum k-vertex cover) to answer the original question to ϕ. In other words,
Student A’s task is to implement a Cook-reduction that reduces 3SAT to Vertex Cover problem.
• Student B should write a method that takes an undirected graph G : (V, E) in whatever data
structures the team decided to use and returns a minimum vertex cover in G. As Student A,
this method should not solve the problem directly. Instead, he/she should reduce the problem in
polynomial time to a corresponding maximum clique problem for Student C to solve and use the
result (some maximum clique) to answer the original minimum Vertex Cover problem. In other
words, Student B’s task is to implement a Cook-reduction that reduces Vertex Cover problem to
Clique problem.
• Student C should write a method that takes an undirected graph G : (V, E) in whatever data
structures the team decided to use and returns a maximum k-clique in G.
According to the description above, the three students’ programs (or methods in one program) need to
communicate to each other. A’s method’s output will be B’s method’s input and B’s method’s output will
be C’s method’s input. Also, Student A’s program will make use of B’s output for his/her own answer,
and B will make use of C’s output for his/her own answer.
It is the team’s agreement to distribute the work load fairly. It is clear that Student B’s task is relatively
easy (just construct the complement graph). To balance the load, for example, Student B may take more
responsibility to implement the program interface such as reading and parsing the text files that contain
graph descriptions, or take care of the output format, or be the corresponding student.
