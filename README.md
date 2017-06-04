# PSZT-Reversi
Projekt na zajęcia z Podstaw Sztucznej Inteligencji na EITI PW.

AI do gry zostało zaimplementowane poprzez algorytm AlphaBeta z iteracyjnym pogłębianiem oraz tablicami transponowań. Tablice te przechowują dolne granice, górne granice lub dokładne wartości z poprzednich wyszukiwań co pozwala używać tych informacji w dalszych wyszukiwaniach, w których to często napotyka się wcześniej znalezione pozycje gry. 

Algorytm wymaga aby klasa gry implementowała interfejs Game z pakietu game.ai w tym aby wykorzystywała klasę Zobrist i udostępniała wyliczane po każdym ruchu klucze Zobrist. Można łatwo go podpiąć pod dowolny inny program byle klasa gry spełniała te wymagania. 

Jego funkcja heurystyczna oceniająca grę (klasa Heuristic) przy ocenie planszy uwzględnia ilość pionków, mobilność graczy, zajmowane pozycje, zajmowanie narożników oraz zajmowanie sąsiedztw narożników. Jest używana do oceny planszy kiedy przeszukiwanie dotarło na ustaloną maksymalną głębokość nawet jeśli plansza nie jest w stanie końcowym oraz do wstępnego sortowania listy ruchów. 

Zanim algorytm zacznie przeszukiwać daną planszę sprawdza czy nie znajduje się ona już w tablicy transponowań, i jeśli wartość z tablicy pochodzi z przeszukiwania z tej samej lub większej głębokości to jest używana odpowiednio jako alfa/beta/wartość dokładna. Dodatkowo jeśli ten wpis zawiera ruch, to ruch ten jest umieszczany na początku listy ruchów do przejrzenia co z dobrym prawdopodobieństwem spowoduje więcej odcięć. 
