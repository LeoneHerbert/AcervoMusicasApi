package br.com.israel.acervomusicasapi.exceptions;

public class AcervoMusicaException extends RuntimeException {
    public AcervoMusicaException() {}

    public AcervoMusicaException(String mensagem) {
        super(mensagem);
    }

    public AcervoMusicaException(Throwable causa) {
        super(causa);
    }

    public AcervoMusicaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
