package dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Dev {
    // Immutable identity keeps Set membership stable as enrollment/progress changes.
    private final UUID id = UUID.randomUUID();
    private String name;
    private Set<Conteudo> subscribedContent = new LinkedHashSet<>();
    private Set<Conteudo> completedContent = new LinkedHashSet<>();

    public void subscribeBootcamp(Bootcamp bootcamp) {
        this.subscribedContent.addAll(bootcamp.getContent());
        bootcamp.getSubscribedDevs().add(this);
    }

    public void progression(){
        Optional<Conteudo> content = this.subscribedContent.stream().findFirst();
        if(content.isPresent()){
            this.completedContent.add(content.get());
            this.subscribedContent.remove(content.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo");
        }
    };

    public double calculateTotalXp(){
        return this.completedContent.stream().mapToDouble(Conteudo::calculateXp).sum();
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Conteudo> getSubscribedContent() {
        return subscribedContent;
    }

    public void setSubscribedContent(Set<Conteudo> subscribedContent) {
        this.subscribedContent = subscribedContent;
    }

    public Set<Conteudo> getCompletedContent() {
        return completedContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(id, dev.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void setCompletedContent(Set<Conteudo> completedContent) {
        this.completedContent = completedContent;

    }
}
