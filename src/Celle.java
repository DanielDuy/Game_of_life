class Celle {
    boolean levende;
    Celle[] naboer;
    int antNaboer;
    int antLevendeNaboer;

    public Celle() {
        levende = false;
        naboer = new Celle[8];
        antNaboer = 0;
        antLevendeNaboer = 0;
    }

    public void settDoed() {
        levende = false;
    }

    public void settLevende() {
        levende = true;
    }

    public boolean erLevende() {
        return levende;
    }

    public char hentStatusTegn() {
        if (levende) {
            return 'O';
        }
        return '.';
    }

    public void leggTilNabo(Celle naboCelle) {
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i] == null) {
                naboer[i] = naboCelle;
                antNaboer += 1;
                break;
            }
        }
    }

    public void tellLevendeNaboer() {
        int antallLevendeNaboer = 0;
        for (int i = 0; i < naboer.length; i++) {
            if (naboer[i].erLevende()) {
                antallLevendeNaboer += 1;
            }
            if ((i+1 != naboer.length) && (naboer[i+1] == null)) {
                break;
            }
        }
        antLevendeNaboer = antallLevendeNaboer;
    }

    public void oppdaterStatus() {
        if (erLevende()) {
            if (antLevendeNaboer < 2 || 3 < antLevendeNaboer) {
                settDoed();
            }
        } else {
            if (antLevendeNaboer == 3) {
                settLevende();
            }
        }
    }
}
