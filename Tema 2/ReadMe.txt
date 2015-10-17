Popa Maria-Catalina 321CC

	<Tema 2>

1. Problema 1 - K-Colorare

	Complexitate temporala : C ^ N - pentru fiecare culoare se genereaza o
													 solutie cu N componente
	Complexitate spatiala : O(N)		

		Se citeste din fisier numerele N, M, C. Se completeaza legaturile 
dintre noduri in matricea muchi, iar legaturile si costurile dintre
culori in matricea culori. Se apeleaza functia back() si se afiseaza 
valorile.
	back() - daca nodul curent este egal cu N ( numarul de noduri ) se
face o copie a solutiei si se valideaza ca s-a gasit cel putin o una.
In caz contrar se parcurg culorile si se verifica daca culoarea este
valida prin functia valid() . Se calculeaza suma partiala si daca suma 
este mai mica decat min pana in acel moment se apeleaza recursiv pentru
nodul urmator. Min se actualizeaza doar in momentul in care am gasit
o solutie completa si costul ei este mai mic.
	valid() - verifica daca vecinii nodului au aceeasi culoare ca cea propusa.
In caz afirmativ return 1;
	sumP() - calculeaza suma costurilor pentru solutia data prin parcurgerea
partii inferioare a matricii.

2. Problema 2 - Expozitie de flori

	Complexitate: (N + V)logN 
	
		Se citesc din fisier datele si se creeaza o matrice cu muchiile, iar vectorul
"vector" retine pentru fiecare nod numarul de vecini. Se pune in coada cu 
prioritati primele valori de tip <nod,nr_muchii> . Coada cu prioritati aranjeaza
elementele astfel incat numarul de arce sa fie minim. Se scoate fiecare element
din coada si se verifica daca a mai fost vizitat . Se verifica daca numarul de 
muchii ramase plus numarul de muchii care ies din graf sa fie par. In caz 
afirmativ se parcurg vecinii si pentru fiecare nod se actulizeaza numarul de 
vecini cu unul in minus ,se completeaza in matricea finala legatura creata si
se adauga in coada nodul dar cu nou numar de muchii. In caz ca numarul calculat
este impar se pune in matrice N - 1 vecini care ies din nod si ultimul care
intra. Se pun in coada noii vecini si se actualizeaza numarul de vecini si 
numarul de muchii care ies din nod. Vectorul check folosete pentru a verifica daca
un nod a fost deja vizitat deoarece in coada se pun aceleasi noduri cu aceleasi
valori dar cu numar de vecini actualizati.

Referinte:
	-problema 1 generare backtracking 
http://www.geeksforgeeks.org/backttracking-set-5-m-coloring-problem/
