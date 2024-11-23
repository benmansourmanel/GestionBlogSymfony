<?php

namespace App\Entity;

use App\Repository\UtilisateurRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: UtilisateurRepository::class)]
class Utilisateur 
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(type: 'integer')]
    /**
     * @Assert\NotBlank(message="Le champ cin est obligatoire")
     * @Assert\Regex(
     *     pattern="/^\d{8}$/",
     *     message="Le champ cin doit contenir 8 chiffres."
     * )
     */
    private ?int $cin = null;

    #[ORM\Column(length: 60)]
    /** 
     * @Assert\Type("string", message="The value {{ value }} is not a valid {{ type }}.")
     * @Assert\NotBlank(message="Nom taille entre [3..20]")
     * @Assert\Length(
     *     min = 3,
     *     max = 20,
     *     minMessage = "le nom doit etre superieur a 3 characters ",
     *     maxMessage = "le nom doit etre inferieur a 20 characters"
     * )
     * @Assert\Regex(
     *     pattern="/^[a-zA-Z]+$/",
     *     message="Ce champ ne doit contenir que des caractères alphabétiques."
     * )
     */
    private ?string $nom = null;

    #[ORM\Column(length: 80)]
    /** 
     * @Assert\Type("string", message="The value {{ value }} is not a valid {{ type }}.")
     * @Assert\NotBlank(message="Nom taille entre [3..20]")
     * @Assert\Length(
     *     min = 3,
     *     max = 20,
     *     minMessage = "le nom doit etre superieur a 3 characters ",
     *     maxMessage = "le nom doit etre inferieur a 20 characters"
     * )
     * @Assert\Regex(
     *     pattern="/^[a-zA-Z]+$/",
     *     message="Ce champ ne doit contenir que des caractères alphabétiques."
     * )
     */
    private ?string $prenom = null;

    #[ORM\Column(length: 200)]
    /**
     * @Assert\Email(message="Veuillez entrer une adresse email valide.")
     * @Assert\Regex(
     *     pattern="/@(gmail\.com|gmail\.fr|esprit\.tn)$/",
     *     message="Veuillez entrer une adresse email valide de Gmail ou Esprit."
     * )
     */
    private ?string $mail = null;

    #[ORM\Column(length: 220)]
    /**
     * @Assert\NotBlank(message="Le champ password est obligatoire")
     * @Assert\Length(
     *     min = 8,
     *     minMessage = "Le mot de passe doit comporter au moins {{ limit }} caractères"
     * )
     */
    private ?string $password = null;

    #[ORM\Column(length: 210)]
    /**
     * @Assert\NotBlank(message="Le champ role est obligatoire")
     */
    private ?string $role = null;

    #[ORM\Column(type: 'string', length: 20)]
    /**
     * @Assert\Regex(
     *     pattern="/^\d{8}$/",
     *     message="Le numéro de téléphone doit contenir exactement 8 chiffres."
     * )
     */
    private ?string $tel = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCin(): ?int
    {
        return $this->cin;
    }

    public function setCin(int $cin): self
    {
        $this->cin = $cin;
        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;
        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;
        return $this;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(string $mail): self
    {
        $this->mail = $mail;
        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;
        return $this;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(string $role): self
    {
        $this->role = $role;
        return $this;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): self
    {
        $this->tel = $tel;
        return $this;
    }

    // Implementing UserInterface methods

    public function getUserIdentifier(): string
    {
        return $this->mail ?? '';
    }

    public function getRoles(): array
    {
        // Return an array of roles
        return [$this->role ?? 'ROLE_USER'];
    }

    public function getSalt(): ?string
    {
        // If you're using bcrypt or Argon2i, you don't need to implement this method
        return null;
    }

    public function eraseCredentials(): void
    {
        // If you store sensitive data on the user, clear it here
    }
}
