package com.book.network.book;

import com.book.network.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;


    public Integer save(BookRequest request, Authentication connectedUser) {
         User user = ((User) connectedUser.getPrincipal());
        Book book = bookMapper.toBook(request);
         book.setOwner(user);
        return bookRepository.save(book).getId();
    }
}
