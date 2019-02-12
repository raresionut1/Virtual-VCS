//321CD_Constantin_Rares_Tema2_POO

***Version Control System***

Pentru fiecare operatie din VCS, am facut o clasa care implementeaza metodele clasei abstracte `VcsOperation`. Aceste comenzi sunt apelate prin metoda `execute()`.

Pe langa operatii, am facut diferite clase pentru lucrul cu branchuri si commituri:

- Clasa `Commit`, ce memoreaza starea curenta a sistemului virtual de fisiere. De asemenea, are si un id unic si un mesaj.
- Clasa `Branch`, care contine o lista de `Commit`-uri, ce contine cel putin 1 commit (*"First Commit"*). Fiecare branch are un nume unic.
- Clasa `MasterBranch`, care mosteneste clasa `Branch`. Aceasta urmeaza design pattern-ul `Singleton`, deoarece intr-un VCS poate exista un singur master branch, deci o singura instanta a clasei. Aceasta clasa are, in plus fata de clasa `Branch`, o lista de liste de branch-uri, pentru memorarea tuturor branch-urilor ce pornesc din fiecare commit.
- Clasa `Staging`, care contine o lista de String-uri, folosita ulterior de catre operatia `StatusOperation`. Fiecare String din lista reprezinta un mesaj specific fiecarei operatii tracked, ce nu a fost inca commit-uita sau stearsa (rollback/ checkout). Aceasta clasa este de asemenea de tipul `Singleton`, deoarece avem nevoie de un singur staging intr-un VCS.
 