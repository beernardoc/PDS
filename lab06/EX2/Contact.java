class Contact{
    private int nmec;
    private String nome;


    Contact(int nmec, String nome) {
        this.nmec=nmec;
        this.nome=nome;
    }

    public int getNmec() {
        return nmec;
    }
    public String getNome() {
        return nome;
    }
    
    public String toString(){
        return this.nome + " " + this.nmec;
    }
}