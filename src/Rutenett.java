class Rutenett {
    int antRader;
    int antKolonner;
    Celle[][] rutene;
    int globalAntallLevende;

    public Rutenett(int innAntRader, int innAntKolonner) {
        antRader = innAntRader;
        antKolonner = innAntKolonner;
        rutene = new Celle[antRader][antKolonner];
    }

    public void lagCelle(int radPlass, int kolPlass){
        Celle nyCelle = new Celle();
        boolean sjanseLevende = Math.random()<=0.3333;
        if (sjanseLevende) {
            nyCelle.settLevende();
        }
        rutene[radPlass][kolPlass] = nyCelle;
    }

    public void fyllMedTilfeldigeCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int n = 0; n < antKolonner; n++) {
                lagCelle(i, n);
            }
        }
    }

    public Celle hentCelle(int innRadPlass, int innKolPlass) {
        if ((0 <= innRadPlass && innRadPlass < antRader) && (0 <= innKolPlass && innKolPlass < antKolonner)) {
            return rutene[innRadPlass][innKolPlass];
        }
        return null;
    }

    public void tegnRutenett() {
        System.out.print("\n");
        for (int i = 0; i < antRader; i++) {
            for (int n = 0; n < antKolonner; n++) {
                System.out.print(rutene[i][n].hentStatusTegn());
            }
            System.out.print("\n");
        }
    }

    public void settNaboer(int innRP, int innKP) {
        for (int i = innRP-1; i < innRP+2; i++) {
            for (int n = innKP-1; n < innKP+2; n++) {
                if ((-1 < i && i < antRader) && (-1 < n && n < antKolonner)) {
                    if (i == innRP && n == innKP) {
                        continue;
                    }
                    rutene[innRP][innKP].leggTilNabo(rutene[i][n]);
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int i = 0; i < antRader; i++) {
            for (int n = 0; n < antKolonner; n++) {
                settNaboer(i, n);
            }
        }
    }

    public int antallLevende() {
        int antallLevendeTeller = 0;
        for (int i = 0; i < antRader; i++) {
            for (int n = 0; n < antKolonner; n++) {
                if (rutene[i][n].erLevende()) {
                    antallLevendeTeller += 1;
                }
            }
        }
        globalAntallLevende = antallLevendeTeller;
        return antallLevendeTeller;
    }

    public int hentGlobalAntLev() {
        return globalAntallLevende;
    }

    public void trekkFraGlobalAntLev() {
        globalAntallLevende = globalAntallLevende - 1;
    }

    public void leggTilFraGlobalAntLev() {
        globalAntallLevende += 1;
    }
}
