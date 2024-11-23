<?php
namespace App\Controller;

use App\Repository\PostRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class PdfController extends AbstractController
{
    #[Route('/pdf/generate', name: 'pdf_generate')]
    public function generatePdf(PostRepository $PostRepository): Response
    {
        // Configure Dompdf options
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Initialize Dompdf with options
        $dompdf = new Dompdf($pdfOptions);
        
        // Fetch data from repository
        $Posts = $PostRepository->findAll();
        
        // Render HTML content for PDF
        $html = $this->renderView('pdf/template.html.twig', [
            'posts' => $Posts,
        ]);
        
        // Load HTML into Dompdf
        $dompdf->loadHtml($html);
        
        // Set paper size and orientation
        $dompdf->setPaper('A4', 'portrait');
        
        // Render the PDF
        $dompdf->render();
        
        // Stream the PDF to the browser for download
        return new Response(
            $dompdf->output(),
            Response::HTTP_OK,
            ['Content-Type' => 'application/pdf']
        );
    }
}
