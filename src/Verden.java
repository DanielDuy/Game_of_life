class Verden {
    Rutenett rutenett;
    int genNr;
    public Verden(int innAntallRader, int innAntallKolonner) {
        rutenett = new Rutenett(innAntallRader, innAntallKolonner);
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
        genNr = 0;
    }

    public void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjons nummer: "+genNr);
    }

    public void oppdatering() {
        for (int i = 0; i < rutenett.antRader; i++) {
            for (int n = 0; n < rutenett.antKolonner; n++) {
                rutenett.rutene[i][n].tellLevendeNaboer();
                rutenett.rutene[i][n].oppdaterStatus();
            }
        }
        rutenett.antallLevende();
        genNr += 1;
    }

    public Rutenett hentRutenett() {
        return rutenett;
    }
}
