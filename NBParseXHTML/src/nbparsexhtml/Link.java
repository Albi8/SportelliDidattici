package nbparsexhtml;

public class Link {

    private String href;
    private String disciplina;
    private String docente;
    private String giorno;
    private String ora;

    public Link() {
        this.href = "";
        this.disciplina = "";
        this.docente = "";
        this.giorno = "";
        this.ora="";
    }

    public Link(String href, String disciplina, String docente, String giorno,String ora) {
        this.href = href.trim();
        this.disciplina = disciplina.trim();
        this.docente = docente.trim();
        this.giorno = giorno.trim();
        this.ora = ora.trim();
    }


    public Link(Link stringa) {
        this.href = stringa.href;
        this.disciplina = stringa.disciplina;
        this.docente = stringa.docente;
        this.giorno = stringa.giorno;
        this.ora = stringa.ora;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href.trim();
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina.trim();
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente.trim();
    }

    public String getGiorno() {
        return giorno;
    }

    public void setClasse(String giorno) {
        this.giorno = giorno.trim();
    }

    public String toString() {
        return "[" + href + "] " + disciplina + ";" + docente + ";" + giorno + ";"+ora;
    }
}
