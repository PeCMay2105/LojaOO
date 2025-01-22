package model;

public class Global {
    public static Pessoa pessoa;
    public static Pessoa getPessoa() {return pessoa;}
    public static void setPessoa(Pessoa _pessoa)
    {
        pessoa = _pessoa;
    }
}
