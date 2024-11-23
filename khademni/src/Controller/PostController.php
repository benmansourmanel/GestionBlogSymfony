<?php

namespace App\Controller;
use App\Entity\Post;
use App\Form\PostType;
use App\Repository\PostRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\DependencyInjection\ParameterBag\ParameterBagInterface;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
#[Route('/post')]
class PostController extends AbstractController
{
    private string $uploadDir;

    public function __construct(ParameterBagInterface $params)
    {
        $this->uploadDir = $params->get('upload_directory');
    }

    #[Route('/', name: 'app_post_index', methods: ['GET'])]
    public function index(PostRepository $postRepository): Response
    {
        return $this->render('post/index.html.twig', [
            'posts' => $postRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_post_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $post = new Post();
        $form = $this->createForm(PostType::class, $post);
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            // Sanitize post content using filterwords function
            $filteredContent = $this->filterwords($post->getContent());
            $post->setContent($filteredContent);
    
            // Handle image upload
            $file = $form->get('image')->getData();
            
            if ($file) {
                $originalFilename = pathinfo($file->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = uniqid().'.'.$file->guessExtension();
    
                try {
                    $file->move(
                        $this->uploadDir,
                        $newFilename
                    );
                } catch (FileException $e) {
                    // Handle file upload exception
                }
    
                $post->setImage($newFilename);
            }
    
            $entityManager->persist($post);
            $entityManager->flush();
    
            return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('post/new.html.twig', [
            'post' => $post,
            'form' => $form,
        ]);
    }
    
    public function filterwords($text)
    {
        $filterWords = array('fokaleya', 'bhim', 'msatek', 'fuck', 'slut', 'fucku');
        $filterCount = count($filterWords);
        $str = "";
        $data = preg_split('/\s+/',  $text);
        foreach($data as $s){
            $g = false;
            foreach ($filterWords as $lib) {
                if($s == $lib){
                    $t = "";
                    for($i =0; $i<strlen($s); $i++) $t .= "*";
                    $str .= $t . " ";
                    $g = true;
                    break;
                }
            }
            if(!$g)
            $str .= $s . " ";
        }
        return trim($str);
    }
    
    
    #[Route('/{id}', name: 'app_post_show', methods: ['GET'])]
    public function show(Post $post): Response
    {
        return $this->render('post/show.html.twig', [
            'post' => $post,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_post_edit', methods: ['GET', 'POST'])]
public function edit(Request $request, Post $post, EntityManagerInterface $entityManager): Response
{
    $form = $this->createForm(PostType::class, $post);
    $form->handleRequest($request);

    if ($form->isSubmitted() && $form->isValid()) {
        // Sanitize post content using filterwords function
        $filteredContent = $this->filterwords($post->getContent());
        $post->setContent($filteredContent);

        $entityManager->flush();

        return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
    }

    return $this->renderForm('post/edit.html.twig', [
        'post' => $post,
        'form' => $form,
    ]);
}

    #[Route('/{id}', name: 'app_post_delete', methods: ['POST'])]
    public function delete(Request $request, Post $post, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$post->getId(), $request->request->get('_token'))) {
            $entityManager->remove($post);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_post_index', [], Response::HTTP_SEE_OTHER);
    }
}
